import SwiftUI

struct CalculatorView: View {
    
    @State var selectedCity = cities[0]
    @State var time = Date.now

    var body: some View {
        NavigationView {
            GeometryReader { g in
                
                VStack(alignment: .leading) {
                    Text("Select city").font(.title.bold())
                    
                    Picker("", selection: $selectedCity) {
                        ForEach(cities, id:\.self) {city in
                            Text(city.name).font(.body).tag(city)
                        }
                    }.onChange(of: selectedCity, perform: { (value) in
                        print(value)
                        print(time)
                        self.time = selectedCity.getCityCurrentDateTime()
                        //time.addingTimeInterval(selectedCity.getCityCurrentDateTime().distance(to: time))
                        print(self.time)
                    })

                    .pickerStyle(.wheel)
                    .frame(maxHeight: g.size.height * 0.2)
                    .clipped()
                    
                    Text("Select \(selectedCity.name) time").font(.title.bold())
                    Text("Time \(time.description(with: .current))")
                    
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
