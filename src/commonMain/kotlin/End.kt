import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class End() : Scene() {
    override suspend fun Container.sceneInit() {
        val sound = resourcesVfs["switch.mp3"].readSound()
        val fourthImage = image(resourcesVfs["4.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
        }
        val fifthImage = image(resourcesVfs["5.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible = false
        }
        val sixthImage = image(resourcesVfs["6.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible=false
        }
        onClick {
            if(fourthImage.visible){
                sound.play()
                fourthImage.visible = false
                fifthImage.visible = true
            } else if(fifthImage.visible){
                sound.play()
                fifthImage.visible = false
                sixthImage.visible = true
            }
        }
    }
}
