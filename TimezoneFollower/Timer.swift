import Foundation

var clockTimer = Timer.publish(every: 0.1, on: .main, in: .common).autoconnect()

func findCityByName(s: String) -> City {
    for c in cities {
        if(c.name == s) {
            return c;
        }
    }
    
    return cities[0];
}
