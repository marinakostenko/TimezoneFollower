import SwiftUI

struct ContentView: View {
    @State private var text = ""
    @State private var showingSheet = false
    @State var citiesCollection = [City]()
    
    var body: some View {
        NavigationView {
            VStack (alignment: .leading){
                ForEach(citiesCollection, id:\.self) {city in
                    NavigationLink {
                        CityDetailedView(city: city)
                    } label: {
                        Text(city.name).searchCompletion(city.name)
                    }
                    .foregroundColor(.black)
                    .padding()
                }
                
                CityList()
            }
            .navigationTitle("World Time")
            .navigationBarTitleDisplayMode(.large)
        }
        .searchable(text: $text,
                    placement: .navigationBarDrawer(displayMode: .always),
                    prompt: "Search cities")
        .onChange(of: text) { index in
            if !index.isEmpty {
                citiesCollection = cities.filter{ $0.name.lowercased().contains(index.lowercased())}
                print("Collection ", citiesCollection.capacity)
            } else {
                citiesCollection = [City]()
            }
            
        }
        
        
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
