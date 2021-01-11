import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.TtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korio.stream.openSync

class Intro() : Scene() {
    override suspend fun Container.sceneInit() {
        val sound = resourcesVfs["switch.mp3"].readSound()
        val title = image(resourcesVfs["0.png"].readBitmap()) {
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible = true
        }
        val firstImage = image(resourcesVfs["1.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible = false
        }
        val secondImage = image(resourcesVfs["2.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible = false
        }
        val thirdImage = image(resourcesVfs["3.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
            visible = false
        }
        onClick {
            if(title.visible){
                sound.play()
                title.visible = false
                firstImage.visible = true
            } else if(firstImage.visible){
                sound.play()
                firstImage.visible = false
                secondImage.visible = true
            } else if(secondImage.visible){
                sound.play()
                secondImage.visible = false
                thirdImage.visible = true
            } else if(thirdImage.visible){
                sound.play()
                sceneContainer.changeTo<Game>()
            }
        }
    }
}
