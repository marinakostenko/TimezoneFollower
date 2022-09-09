import SwiftUI

struct CalculatorView: View {
    @State var time = Date()
    @State var selectedCity = cities[0].name

    var body: some View {
        NavigationView {
            GeometryReader { g in
                
                VStack(alignment: .leading) {
                    Text("Select city").font(.title.bold())
                    
                    Picker("", selection: $selectedCity) {
                        ForEach(cities, id:\.self) {city in
                            Text(city.name).font(.body).tag(city.name)
                        }
                    }

                    .pickerStyle(.wheel)
                    .frame(maxHeight: g.size.height * 0.2)
                    .clipped()
                    
                    Text("Select \(selectedCity) time").font(.title.bold())
                    
                    DatePicker("", selection: $time)
                        .datePickerStyle(WheelDatePickerStyle()).padding()
                        .clipped()
                    
                    Text("Your time").font(.title.bold())
                    Text(user.userDateTime(d: time))
                    
                }
                .padding()
                
            }
            .navigationTitle("Time Calculator")
            
        }
        
        
    }
}

struct CalculatorView_Previews: PreviewProvider {
    static var previews: some View {
        CalculatorView()
    }
}
