import SwiftUI

struct ContactsList: View {
    
    var body: some View {
        List(contacts, id:\.self) { contact in
            ContactRow(user: contact)
        }
    }
}

struct ContactsList_Previews: PreviewProvider {
    static var previews: some View {
        ContactsList()
    }
}
