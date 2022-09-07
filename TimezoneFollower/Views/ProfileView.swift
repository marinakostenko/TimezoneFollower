import SwiftUI

struct ProfileView: View {
    var user: User
    @State var time = Date()
    let clockTimer = Timer.publish(every: 0.1, on: .main, in: .common).autoconnect()
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                CircleAvatar(avatar: user.avatar, width: 100, height: 100)
                Text(user.name).font(.title)
                
                HStack(alignment: .center) {
                    Image(systemName: "mappin.circle.fill")
                    Text(user.location)
                }.padding()
                
                Text(time, style: .date)
                Text(time, style: .time)
                    .onReceive(clockTimer) { _ in
                        self.time = Date()
                    }
                
                
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
