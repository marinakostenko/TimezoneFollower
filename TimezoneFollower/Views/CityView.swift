import SwiftUI

struct CityView: View {
    var city: City
    
    
    var body: some View {
        
        ZStack {
            Color.clear
                .aspectRatio(1, contentMode: .fit)
                .overlay(
                    city.image
                        .resizable()
                        .scaledToFill()
                    
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
                    Text(city.country)
                        .foregroundColor(.white)
                        .bold()
                        .scaledToFill()
                        .minimumScaleFactor(0.5)
                        .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.1: g.size.height * 0.1))
                    Spacer()
                    
                        VStack {
                            Text(city.time)
                                .foregroundColor(.white)
                                .bold()
                                .scaledToFill()
                                .minimumScaleFactor(0.5)
                            .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.2: g.size.height * 0.2))
                            Text(city.date)
                                .foregroundColor(.white)
                                .scaledToFill()
                                .minimumScaleFactor(0.5)
                                .font(.system(size: g.size.height > g.size.width ? g.size.width * 0.08: g.size.height * 0.08))
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
