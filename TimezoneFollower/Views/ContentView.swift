import SwiftUI

struct ContentView: View {
    @State private var text = ""
    
    var body: some View {
        NavigationView {
            VStack (alignment: .leading){
                CityList()
                    .searchable(text: $text,
                                placement: .navigationBarDrawer(displayMode: .always),
                                prompt: "Search cities") {
                        
                        ForEach(cities, id:\.self) {city in
                            Text(city.name).searchCompletion(city.name)
                        }
                    }
            }
            .navigationTitle("World Time")
        }
        
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
