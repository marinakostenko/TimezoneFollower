import SwiftUI

struct ContactRow: View {
    var user: User
    @State var time = Date()

    var body: some View {
        HStack {
            CircleAvatar(avatar: user.avatar, size: 50).padding()
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
