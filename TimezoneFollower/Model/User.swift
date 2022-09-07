
import Foundation
import SwiftUI

struct User: Hashable, Codable {
    var id: String
    var name: String
    var location: String
    var phoneNumber: String
    var timeZone: String
    
    var avatar: Image {
        Image(systemName: "person")
    }
    
    func userDateTime(d: Date) -> String {
        var f = Date.FormatStyle.dateTime
        f.timeZone = TimeZone(identifier: timeZone)!
        
        return d.formatted(f)
    }
}

