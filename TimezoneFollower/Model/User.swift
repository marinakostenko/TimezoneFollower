
import Foundation
import SwiftUI

struct User: Hashable, Codable {
    var name: String
    var location: String
    var phoneNumber: String
    var contacts: [String]
    
    var avatar: Image {
        Image(systemName: "person")
    }
}
