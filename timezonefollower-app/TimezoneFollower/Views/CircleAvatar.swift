import SwiftUI

struct CircleAvatar: View {
    var avatar: Image
    var size: CGFloat
    
    var body: some View {
        avatar
            .resizable()
            .padding(EdgeInsets.init(top: size * 0.1, leading: size * 0.1, bottom: size * 0.1, trailing: size * 0.1))
            .clipShape(Circle())
            .scaledToFit()
            .frame(width: size, height: size)
            .overlay(Circle().stroke(.gray, lineWidth: size * 0.05))
    }
}

struct CircleAvatar_Previews: PreviewProvider {
    static var previews: some View {
        CircleAvatar(avatar: user.avatar, size: 100)
    }
}
