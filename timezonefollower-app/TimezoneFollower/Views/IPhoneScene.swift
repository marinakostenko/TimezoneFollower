
import SwiftUI

struct IPhoneScene: Scene {
    var body: some Scene {
        WindowGroup {
            TabView {
                ContentView()
                    .tabItem({
                        Label("Times", systemImage: "clock")
                    })
                
                CalculatorView()
                    .tabItem({
                        Label("Calculator", systemImage: "hourglass.circle")
                    })
                
                ProfileView(user: user)
                    .tabItem({
                        Label("Profile", systemImage: "person.crop.circle")
                    })
            }
        }
    }
}
