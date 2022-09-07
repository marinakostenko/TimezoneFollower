import SwiftUI

struct CityView: View {
    var city: City
    @State var time = Date()
    
    var body: some View {
        
        ZStack {
            Color.black
                .aspectRatio(1, contentMode: .fit)
                .overlay(
                    city.image
                        .resizable()
                        .scaledToFill()
                        .opacity(0.8)
                    
                )
                .clipShape(Rectangle())
            
            GeometryReader { g in
                VStack(alignment: .leading) {
                    Text(city.name)
                        .foregroundColor(.white)
                        .bold()
                        .scaledToFill()
                        .minimumScaleFactor(0.5)
                        .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.2: g.size.height * 0.2))
                        //.padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                    Text(city.country)
                        .foregroundColor(.white)
                        .bold()
                        .scaledToFill()
                        .minimumScaleFactor(0.5)
                        .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.1: g.size.height * 0.1))
                        //.padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                    
                    HStack(alignment: .top) {
                        //city.getCityContacts()[0]
                        
                        CircleAvatar(avatar: Image(systemName: "person"), size: g.size.width * 0.15)
                        CircleAvatar(avatar: Image(systemName: "person"), size: g.size.width * 0.15)
                        CircleAvatar(avatar: Image(systemName: "person"), size: g.size.width * 0.15)
                        CircleAvatar(avatar: Image(systemName: "ellipsis.circle.fill"), size: g.size.width * 0.15)
                    }
                    
                    Spacer()
                    
                    VStack(alignment: .leading) {
                        Text(city.cityDate(d: time))
                            .foregroundColor(.white)
                           // .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                            .scaledToFill()
                            .minimumScaleFactor(0.5)
                            .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.08: g.size.height * 0.08))
                            .onReceive(clockTimer) { _ in
                                self.time = Date()
                            }
                        Text(city.cityTime(d: time))
                            .foregroundColor(.white)
                            .bold()
                            //.padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 0))
                            .scaledToFill()
                            .minimumScaleFactor(0.5)
                            .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.2: g.size.height * 0.2))
                            .onReceive(clockTimer) { _ in
                                self.time = Date()
                            }
                        
                    }
    
                    
                    
                }
            }.aspectRatio(contentMode: .fit).padding()
            
        }
    }
}

struct CityView_Previews: PreviewProvider {
    
    static var previews: some View {
        CityView(city: cities[0])
    }
}
