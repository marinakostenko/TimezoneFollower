import SwiftUI

struct CircleView: View {
    var body: some View {
            Image("van_city")
                .aspectRatio(contentMode: .fit)
                .clipShape(Circle())
                .overlay(Circle().stroke(.white, lineWidth: 4))
                .shadow(radius: 8)
      
    }
}

struct CircleView_Previews: PreviewProvider {
    static var previews: some View {
        CircleView()
            
    }
}
