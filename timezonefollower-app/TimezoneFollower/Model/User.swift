
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
        var f = Date.FormatStyle()
            .year()
            .month(.abbreviated)
            .day(.twoDigits)
            .hour(.defaultDigits(amPM: .abbreviated)).minute(.twoDigits)
        f.timeZone = TimeZone(identifier: timeZone)!
        
        return d.formatted(f)
    }
    
    func convertSelectedDateTime(date: Date, selectedTimeZone: TimeZone) -> Date {
        let selectedOffset = TimeInterval(selectedTimeZone.secondsFromGMT(for: date))
        let currentOffeset = TimeInterval(TimeZone(identifier: timeZone)!.secondsFromGMT(for: date))

        return date.addingTimeInterval(currentOffeset - selectedOffset)
    }
}

