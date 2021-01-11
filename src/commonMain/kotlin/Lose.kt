import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import kotlin.random.Random

class Lose() : Scene() {
    override suspend fun Container.sceneInit() {
        val sound1 = resourcesVfs["lose/GameOver.mp3"].readSound()
        val sound2 = resourcesVfs["lose/PodvelKakGovoritsya.mp3"].readSound()
        val sound3 = resourcesVfs["lose/Zaraza.mp3"].readSound()
        val sound4 = resourcesVfs["lose/ZaSho.mp3"].readSound()
        var rand1=Random.nextInt(0,4)
        if(rand1==0){
            sound1.play()
        } else if(rand1==1){
            sound2.play()
        } else if(rand1==2){
            sound3.play()
        } else if(rand1==3){
            sound4.play()
        }
        val loseImage = image(resourcesVfs["lose.png"].readBitmap()){
            position(0, 0)
            width=720.0
            height=1280.0
        }
        val potracheno = image(resourcesVfs["gameover.png"].readBitmap()){
            position(30, 50)
            width = 650.0
            height = 180.0
        }
        val comicsFrames = image(resourcesVfs["rotatedBlackLines.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
        }
        onClick(){
            launchImmediately { sceneContainer.changeTo<Game>() }
        }
    }
}
