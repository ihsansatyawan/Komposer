#!/bin/bash

set -e  # Stop if any error occurs

echo "🚀 Starting setup for Komposer..."

# 1. Check for XcodeGen
if ! [ -x "$(command -v xcodegen)" ]; then
  echo "⚙️ Installing XcodeGen..."
  brew install xcodegen
else
  echo "✅ XcodeGen Installed ($(xcodegen --version))."
fi

# 2. Check for SwiftGen
if ! [ -x "$(command -v swiftgen)" ]; then
  echo "⚙️ Installing SwiftGen..."
  brew install swiftgen
else
  echo "✅ SwiftGen Installed ($(swiftgen --version))."
fi

# 3. Generate Code from Resources (SwiftGen)
echo "🎨 Generating Resources with SwiftGen..."
swiftgen config run

# 4. Generate Xcode project
echo "🔨 Generating Xcode project..."
xcodegen generate

# 5. Output Versions for Debugging
echo "💻 System Info:"
xcodebuild -version

echo "✨ Setup done!"
echo "📂 Opening Komposer.xcodeproj..."

# 6. Open Project (Using .xcodeproj since there is no CocoaPods workspace)
open Komposer.xcodeproj