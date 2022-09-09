import Foundation
import SwiftUI

struct City: Hashable, Codable {
    var name: String
    var country: String
    var timeZone: String
    var imageName: String
    
    var image: Image {
        Image(imageName)
    }
    
    func cityDate(d: Date) -> String {
        var f = Date.FormatStyle()
            .year()
            .month(.abbreviated)
            .day(.twoDigits)
        f.timeZone = TimeZone(identifier: timeZone)!
        
        return d.formatted(f)
    }
    
    func cityTime(d: Date) -> String {
        var f = Date.FormatStyle().hour(.defaultDigits(amPM: .abbreviated)).minute(.twoDigits)
        f.timeZone = TimeZone(identifier: timeZone)!
        
        return d.formatted(f)
    }
    
    func getCityCurrentDateTime() -> Date {
        let nowUTC = Date()
        let timeZoneOffset = Double(TimeZone(identifier: timeZone)!.secondsFromGMT(for: nowUTC))
        guard let localDate = Calendar.current.date(byAdding: .second, value: Int(timeZoneOffset), to: nowUTC) else {return Date()}
        return localDate
//        let f = DateFormatter()
//        f.dateFormat = "yyyy-dd-MM HH:mm:ss Z"
//        //f.timeZone = TimeZone(identifier: timeZone)!
//        f.locale = Locale(identifier: Locale.current.regionCode!)
//        let str = f.string(from: Date.now)
//        print("locale ", Locale.current.regionCode!)
//        print("str ", str)
//
//        let parseStrategy = Date.ParseStrategy(format: "\(year: .defaultDigits)-\(day: .twoDigits)-\(month: .twoDigits) \(hour: .defaultDigits(clock: .twelveHour, hourCycle: .zeroBased)):\(minute: .defaultDigits):\(second: .defaultDigits) \(timeZone: .iso8601(.short))", timeZone: .current)
//        var date = f.date(from: str)!//try! Date(str, strategy: parseStrategy)
//        print("date ", date)
       // return date
    }
    
    func getCityContacts() -> [User] {
        var cityContacts = [User]()
        
        for contact in contacts {
            if(contact.timeZone == timeZone) {
                cityContacts.append(contact)
            }
        }
        
        return cityContacts
    }
}

