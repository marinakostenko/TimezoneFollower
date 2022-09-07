import SwiftUI

struct ProfileView: View {
    var user: User
    @State var time = Date()
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                CircleAvatar(avatar: user.avatar, size: 100)
                Text(user.name).font(.title)
                
                HStack(alignment: .center) {
                    Image(systemName: "mappin.circle.fill")
                    Text(user.location)
                }.padding()
                
                Text(user.userDateTime(d: time))
                    .onReceive(clockTimer) { _ in
                        self.time = Date()
                    }
                    .padding()
                
                
                ContactsList()
                
            }
            .navigationTitle("Profile")
        }
    }
}


struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView(user: user)
    }
}
