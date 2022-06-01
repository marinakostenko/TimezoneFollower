import Foundation
import SwiftUI

struct City: Hashable, Codable {
    var name: String
    var country: String
    var timestamp: Double
    
    private var imageName: String
    var image: Image {
        Image(imageName)
    }
}
