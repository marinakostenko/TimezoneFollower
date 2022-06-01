import SwiftUI
import UIKit

struct ContentView: View {
    var body: some View {
        VStack (alignment: .leading){
            HStack(){
                Text("Clock and Weather")
                    .font(.title)
                    .foregroundColor(.gray)
                Spacer()
                Button {
                    print("hello")
                } label: {
                    Label("Add clock", systemImage:"plus.circle")
                        .labelStyle(.iconOnly)
                        .foregroundColor(.yellow)
                }
                
            }.padding()
            
            
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
