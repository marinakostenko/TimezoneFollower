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
    
    func getCityContacts() -> [User] {
        var cityContacts = [User]()
        
        for contact in contacts {
            if(contact.location == name) {
                cityContacts.append(contact)
            }
        }
        
        return cityContacts
    }
}

