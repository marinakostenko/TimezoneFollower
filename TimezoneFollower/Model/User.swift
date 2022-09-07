
import Foundation
import SwiftUI

struct User: Hashable, Codable {
    var id: String
    var name: String
    var location: String
    var phoneNumber: String
    
    var avatar: Image {
        Image(systemName: "person")
    }
    
}

