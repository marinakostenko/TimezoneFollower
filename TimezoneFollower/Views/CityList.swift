import SwiftUI

struct CityList: View {
    var columns = [GridItem(.adaptive(minimum: 150, maximum: 300)), GridItem(.adaptive(minimum: 150, maximum: 300))]
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVGrid(columns: columns) {
                ForEach(cities, id:\.self) {city in
                    CityView(city: city)
                }
            }.padding()
        }
    }
}

struct CityList_Previews: PreviewProvider {
    static var previews: some View {
        CityList()
    }
}
