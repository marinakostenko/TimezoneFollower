import SwiftUI

struct CircleAvatar: View {
    var avatar: Image
    var width: CGFloat
    var height: CGFloat
    
    var body: some View {
        Image(systemName: "person")
            .resizable()
            .padding()
            .clipShape(Circle())
            .scaledToFit()
            .frame(width: width, height: width)
            .overlay(Circle().stroke(.yellow, lineWidth: 4))
            .padding()
    }
}

struct CircleAvatar_Previews: PreviewProvider {
    static var previews: some View {
        CircleAvatar(avatar: user.avatar, width: 100, height: 100)
    }
}
