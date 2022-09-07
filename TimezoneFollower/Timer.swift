import Foundation

var clockTimer = Timer.publish(every: 0.1, on: .main, in: .common).autoconnect()
