import SwiftUI

struct ContentView: View {
    @State private var text = ""
    @State private var showingSheet = false
    
    //TODO some weird issue with sheet - recheck
    
    @State private var selectedCity = cities[0]
    @State private var showCitiesGrid = true
    @State private var showNotFound = false
    @State var citiesCollection = [City]()
    
    var body: some View {
        NavigationView {
            VStack (alignment: .leading){
                ForEach(citiesCollection, id:\.self) {city in
                    Button(city.name) {
                        showingSheet = true
                        selectedCity = city
                        print(selectedCity.name)
                    }
                    .sheet(isPresented: $showingSheet,
                           onDismiss: {showingSheet = false},
                           content: {CityDetailedView(city: selectedCity, sheetView: true)})
                }
                
                if showCitiesGrid {
                    CityList()
                }
                
                if showNotFound {
                    Text("No search results").padding()
                }
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
                showCitiesGrid = false
                
                if(citiesCollection.isEmpty) {
                    showNotFound = true
                } else {
                    showNotFound = false
                }
                
            } else {
                citiesCollection = [City]()
                showCitiesGrid = true
                showNotFound = false
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
