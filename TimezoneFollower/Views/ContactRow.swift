import SwiftUI

struct ContactRow: View {
    var user: User
    
    var body: some View {
        HStack {
            CircleAvatar(avatar: user.avatar, width: 50, height: 50)
            Text(user.name)
            
            Spacer()
        }
    }
}

struct ContactRow_Previews: PreviewProvider {
    static var previews: some View {
        ContactRow(user: user)
    }
}
