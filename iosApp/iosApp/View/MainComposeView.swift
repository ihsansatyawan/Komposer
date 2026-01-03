//
// Created by Ihsan Satyawan on 03/01/26.
//

import Shared
import SwiftUI

private struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct MainComposeView: View {
    var body: some View {
        ComposeView().ignoresSafeArea()
    }
}