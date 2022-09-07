import SwiftUI

struct ContactRow: View {
    var user: User
    @State var time = Date()
    let clockTimer = Timer.publish(every: 0.1, on: .main, in: .common).autoconnect()
    
    var body: some View {
        HStack {
            CircleAvatar(avatar: user.avatar, width: 50, height: 50)
            Text(user.name)
            
            Spacer()
            
            Text(user.userDateTime(d: time))
                .font(.footnote)
                .onReceive(clockTimer) { _ in
                    self.time = Date()
                }
                .padding()
            
        }
    }
}

struct ContactRow_Previews: PreviewProvider {
    static var previews: some View {
        ContactRow(user: contacts[1])
    }
}
