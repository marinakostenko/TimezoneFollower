import SwiftUI

struct CityView: View {
    var city: City
    
    var body: some View {
        city.image
            .resizable()
            .frame(width: 155, height: 155)
            .cornerRadius(5)
            .padding()
    }
}

struct CityView_Previews: PreviewProvider {
    static var previews: some View {
        CityView(city: cities[0])
    }
}
