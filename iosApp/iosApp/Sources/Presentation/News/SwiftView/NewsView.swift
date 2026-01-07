//
//  NewsView.swift
//  iosApp
//
//  Created by Ihsan Satyawan on 05/01/26.
//

import Shared
import SwiftUI

struct NewsView: View {
    @State private var viewModel = NewsInjector().newsViewModel
    @State private var state: NewsState = .init(
        isLoading: false,
        news: [],
        error: nil
    )

    var body: some View {
        NewsContentView(state: state)
            .onAppear {
                Task {
                    for await state in viewModel.state {
                        self.state = state
                    }
                }
                
                viewModel.getNews()
            }
            .onDisappear {
                viewModel.clear()
            }
    }
}

private struct NewsContentView: View {
    let state: NewsState

    var body: some View {
        Group {
            if state.isLoading {
                LoadingView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else if let error = state.error {
                ErrorView(message: error)
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else if !state.news.isEmpty {
                NewsListView(news: state.news)
                    .padding(16)
            }
        }
    }
}

private struct LoadingView: View {
    var body: some View {
        VStack(alignment: .center, spacing: 0) {
            ProgressView()
        }
    }
}

private struct ErrorView: View {
    let message: String

    var body: some View {
        VStack {
            Text(message)
        }
    }
}

private struct NewsListView: View {
    let news: [News]

    var body: some View {
        VStack {
            VStack(alignment: .center) {
                Text(Greeting().greet())
                Text("Here is today's news :")
            }

            Spacer()
                .frame(height: 16)

            ScrollView {
                LazyVStack(spacing: .zero) {
                    ForEach(news, id: \.self) { item in
                        NewsItem(news: item)
                    }
                }
            }
        }
    }
}

private struct NewsItem: View {
    let news: News

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            AsyncImage(url: URL(string: news.imageUrl)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(maxWidth: .infinity)
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(maxWidth: .infinity)
                        .clipped()
                case .failure(let error):
                    Text(error.localizedDescription)
                        .font(.caption)
                @unknown default:
                    EmptyView()
                }
            }

            Text(news.title)
                .font(.title2)
                .fontWeight(.bold)
                .lineLimit(2)
                .truncationMode(.tail)
        }
        .padding(.vertical, 16)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

#Preview {
    NewsView()
}
