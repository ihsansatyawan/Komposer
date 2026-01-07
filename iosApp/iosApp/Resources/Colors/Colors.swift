// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen
import SwiftUI
import UIKit

/// Usage:
/// SwiftUI: `ColorAsset.myColor`
/// UIKit:   `ColorAsset.myColorUI`
internal enum ColorAsset {
  internal static let accentColor = Color("AccentColor")
  internal static let accentColorUI = UIColor(named: "AccentColor")!
}

internal extension Color {
  static func fromAsset(_ asset: Color) -> Color {
    return asset
  }
}