# 1. Define the path for the Pods version
PODS_SWIFTGEN="${PODS_ROOT}/SwiftGen/bin/swiftgen"
export PATH="$PATH:/opt/homebrew/bin"

if [[ -f "$PODS_SWIFTGEN" ]]; then
  # Use the CocoaPods version if it exists
  "$PODS_SWIFTGEN" config run
elif which swiftgen >/dev/null; then
  # Fallback to the system-installed version (Homebrew)
  swiftgen config run
else
  echo "error: SwiftGen not found. Install it via 'brew install swiftgen' or CocoaPods."
  exit 1
fi