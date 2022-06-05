import SwiftUI

struct CityView: View {
    var city: City
    
    var body: some View {
        Color.clear
            .aspectRatio(1, contentMode: .fit)
            .overlay(
                city.image
                    .resizable()
                    .scaledToFill()
                )
            .clipShape(Rectangle())
    }
}

struct CityView_Previews: PreviewProvider {
    static var previews: some View {
        CityView(city: cities[0])
    }
}
