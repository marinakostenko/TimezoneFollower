import SwiftUI

struct ContactsList: View {
    var users: [User]
    
    var body: some View {
        List(users, id:\.self) { contact in
            ContactRow(user: contact)
        }
    }
}

struct ContactsList_Previews: PreviewProvider {
    static var previews: some View {
        ContactsList(users: contacts)
    }
}
