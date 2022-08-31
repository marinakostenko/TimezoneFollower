import Foundation
import SwiftUI

struct City: Hashable, Codable {
    var name: String
    var country: String
    var timestamp: Double
    var temperature: String
    
    //private var imageName: String
    var image: Image {
        Image(name)
    }
    
    var time: String {
        let date = Date(timeIntervalSince1970: timestamp)
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "HH:mm"
        let strDate = dateFormatter.string(from: date)
        return strDate
    }
    
    var date: String {
        let date = Date(timeIntervalSince1970: timestamp)
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MMM-dd"
        let strDate = dateFormatter.string(from: date)
        return strDate
    }
}
