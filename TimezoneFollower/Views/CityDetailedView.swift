import SwiftUI

struct CityDetailedView: View {
    var city: City
    var sheetView: Bool
    @State var time = Date()
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        GeometryReader { g in
            VStack(alignment: .center) {
                ZStack(alignment: .center) {
                    city.image
                        .resizable()
                        .frame(maxHeight: g.size.height * 0.5)
                        .ignoresSafeArea(edges: .top)
                    
                    VStack {
                        if(sheetView) {
                            HStack(alignment: .center) {
                                Button("Cancel") {
                                    presentationMode.wrappedValue.dismiss()
                                }
                                
                                Spacer()
                                
                                Button("Add") {
                                    print("add ", city.name)
                                }
                            }.padding()
                        }
                        Text(city.name)
                            .foregroundColor(.white)
                            .bold()
                            .scaledToFit()
                            .minimumScaleFactor(0.5)
                            .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.2: g.size.height * 0.2))
                        Text(city.country)
                            .foregroundColor(.white)
                            .bold()
                            .scaledToFit()
                            .minimumScaleFactor(0.5)
                            .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.1: g.size.height * 0.1))
                        Spacer()
                    }
                }
                
                VStack(alignment: .trailing) {
                    Text(city.cityDate(d: time))
                        .foregroundColor(.black)
                        .scaledToFill()
                        .minimumScaleFactor(0.5)
                        .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.08: g.size.height * 0.08))
                        .onReceive(clockTimer) { _ in
                            self.time = Date()
                        }
                    Text(city.cityTime(d: time))
                        .foregroundColor(.black)
                        .bold()
                        .scaledToFill()
                        .minimumScaleFactor(0.5)
                        .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.2: g.size.height * 0.2))
                        .onReceive(clockTimer) { _ in
                            self.time = Date()
                        }
                }
                
                Spacer()
                
                ContactsList(users: city.getCityContacts())
                
                
            }
            
        }
    }
}

struct CityDetailedView_Previews: PreviewProvider {
    static var previews: some View {
        CityDetailedView(city: cities[0], sheetView: false)
    }
}
