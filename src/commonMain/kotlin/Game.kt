import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.TtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import kotlin.random.Random

class Game() : Scene() {
    override suspend fun Container.sceneInit() {
        val sound1 = resourcesVfs["jumpSound/AhahaDavai.mp3"].readSound()
        val sound2 = resourcesVfs["jumpSound/Opa.mp3"].readSound()
        val sound3 = resourcesVfs["jumpSound/OtyeSka.mp3"].readSound()
        val sound4 = resourcesVfs["jumpSound/WoHoo.mp3"].readSound()
        val bgStatic = image(resourcesVfs["bgStatic.png"].readBitmap()) {
            position(0, 0)
        }
        val bgDynamic1 = image(resourcesVfs["bgDynamic.png"].readBitmap()) {
            position(0, 640)
        }
        val bgDynamic2 = image(resourcesVfs["bgDynamic.png"].readBitmap()) {
            position(bgDynamic1.width.toInt(), 640)
        }
        val rectCollider = solidRect(185, 200, Colors.WHEAT){
            position(60, 960)
            visible = false
        }
        val gladRide = image(resourcesVfs["01.png"].readBitmap()){
            position(20, 920)
            width=256.0
            height=256.0
        }
        val gladPreJump = image(resourcesVfs["02.png"].readBitmap()){
            position(20, 920)
            visible = false
            width=256.0
            height=256.0
        }
        val gladJump = image(resourcesVfs["03.png"].readBitmap()){
            position(20, 920)
            visible = false
            width=256.0
            height=256.0
        }
        var rand = Random.nextInt(0,3)
        var distance = Random.nextInt(10,100)
        var collider1 = image(resourcesVfs["c1.png"].readBitmap()){
            position(720*2+distance, 1073)
            width=90.0
            height=90.0
        }
        var collider2 = image(resourcesVfs["c2.png"].readBitmap()){
            position(720*2+distance, 1073)
            width=90.0
            height=90.0
        }
        var collider3 = image(resourcesVfs["c3.png"].readBitmap()){
            position(720*2+distance, 1073)
            width=90.0
            height=90.0
        }
        var lose = false

        val numSprite = resourcesVfs["num.png"].readBitmap()
        val numAnimation0=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginLeft = 0, columns=1, rows=1)
        val numAnimation1=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginLeft = 85, columns=1, rows=1)
        val numAnimation2=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginLeft = 170, columns=1, rows=1)
        val numAnimation3=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginLeft = 255, columns=1, rows=1)
        val numAnimation4=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginLeft = 341, columns=1, rows=1)
        val numAnimation5=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginTop = 116, marginLeft = 0, columns=1, rows=1)
        val numAnimation6=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginTop = 116, marginLeft = 85, columns=1, rows=1)
        val numAnimation7=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginTop = 116, marginLeft = 170, columns=1, rows=1)
        val numAnimation8=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginTop = 116, marginLeft = 255, columns=1, rows=1)
        val numAnimation9=SpriteAnimation(spriteMap=numSprite, spriteWidth=85, spriteHeight=116, marginTop = 116, marginLeft = 340, columns=1, rows=1)
        val numFirst = sprite(numAnimation5)
        numFirst.playAnimation()
        numFirst.xy(130, 50)
        numFirst.width=200.0
        numFirst.height=200.0
        val numSecond = sprite(numAnimation4)
        numSecond.playAnimation()
        numSecond.xy(350, 50)
        numSecond.width=200.0
        numSecond.height=200.0
        val comicsFrames = image(resourcesVfs["rotatedBlackLines.png"].readBitmap()){
            position(0, 0)
            width = 720.0
            height = 1280.0
        }

        var jumpAnimationFinished = true
        var jumpAnimation=false
        var preJumpAnimationNum = 15
        var jumpAnimationNum = 60

        var points = 54
        var firstNum = points/10
        var secondNum = points%10
        var rand1=0
        onClick() {
            if(bgStatic.visible) {
                rand1=Random.nextInt(0,4)
                if(rand1==0){
                    sound1.play()
                } else if(rand1==1){
                    sound2.play()
                } else if(rand1==2){
                    sound3.play()
                } else if(rand1==3){
                    sound4.play()
                }
                if (jumpAnimationFinished) {
                    jumpAnimation = true
                    jumpAnimationFinished = false
                }
            }
        }
        addUpdater {
            if(bgStatic.visible) {
                    if (rand == 0) {
                        collider1.x -= 8
                        if (collider1.collidesWith(rectCollider)) {
                            launchImmediately { sceneContainer.changeTo<Lose>() }
                        }
                        if (collider1.x < -collider1.width) {
                            collider1.x = 720 + distance.toDouble()
                            rand = Random.nextInt(0, 2)
                            distance = Random.nextInt(10, 100)
                            points--
                        }
                    } else if (rand == 1) {
                        collider2.x -= 8
                        if (collider2.collidesWith(rectCollider)) {
                            launchImmediately { sceneContainer.changeTo<Lose>() }
                        }
                        if (collider2.x < -collider2.width) {
                            collider2.x = 720 + distance.toDouble()
                            rand = Random.nextInt(0, 2)
                            distance = Random.nextInt(10, 100)
                            points--
                        }
                    } else if (rand == 2) {
                        collider3.x -= 8
                        if (collider3.collidesWith(rectCollider)) {
                            launchImmediately { sceneContainer.changeTo<Lose>() }
                        }
                        if (collider3.x < -collider3.width) {
                            collider3.x = 720 + distance.toDouble()
                            rand = Random.nextInt(0, 2)
                            distance = Random.nextInt(10, 100)
                            points--
                        }
                    }
                    if (bgDynamic1.x <= -bgDynamic1.width) {
                        bgDynamic1.x = 0.0
                        bgDynamic2.x = bgDynamic1.width
                    }
                    bgDynamic1.x -= 3
                    bgDynamic2.x -= 3
                    if (jumpAnimation) {
                        if (preJumpAnimationNum > 0) {
                            preJumpAnimationNum--
                            if (gladRide.visible) {
                                gladRide.visible = false
                                gladPreJump.visible = true
                            }
                            if (preJumpAnimationNum <= 1) {
                                if (gladPreJump.visible) {
                                    gladPreJump.visible = false
                                    gladJump.visible = true
                                    rectCollider.height = 140.0
                                }
                            }
                        } else if (jumpAnimationNum > 0) {
                            if (jumpAnimationNum > 45) {
                                rectCollider.y -= 12
                                gladJump.y -= 12
                                gladPreJump.y -= 12
                            } else if (jumpAnimationNum > 30 && jumpAnimationNum <= 45) {
                                if (!gladPreJump.visible) {
                                    gladPreJump.visible = true
                                    gladJump.visible = false
                                    rectCollider.height = 160.0
                                }
                            } else {
                                rectCollider.y += 6
                                gladJump.y += 6
                                gladPreJump.y += 6
                            }
                            jumpAnimationNum--
                        } else if (jumpAnimationNum <= 0) {
                            jumpAnimation = false
                            preJumpAnimationNum = 15
                            jumpAnimationNum = 60
                            if (gladJump.visible) {
                                gladJump.visible = false
                                gladRide.visible = true
                            }
                            jumpAnimationFinished = true
                        }
                    }
            }
        }

        var numFirstSwitch1 = true
        var numFirstSwitch2 = false
        var numSecondSwitch1 = true
        var numSecondSwitch2 = false
        addUpdater {
            firstNum = points / 10
            secondNum = points % 10
            if (firstNum == 5) {
                if(numFirstSwitch1){
                    numFirst.playAnimation(numAnimation5)
                    numFirstSwitch1 = false
                    numFirstSwitch2 = true
                }
            } else if (firstNum == 4) {
                if(numFirstSwitch2){
                    numFirst.playAnimation(numAnimation4)
                    numFirstSwitch1 = true
                    numFirstSwitch2 = false
                }
            } else if (firstNum == 3) {
                if(numFirstSwitch1){
                    numFirst.playAnimation(numAnimation3)
                    numFirstSwitch1 = false
                    numFirstSwitch2 = true
                }
            } else if (firstNum == 2) {
                if(numFirstSwitch2){
                    numFirst.playAnimation(numAnimation2)
                    numFirstSwitch1 = true
                    numFirstSwitch2 = false
                }
            } else if (firstNum == 1) {
                if(numFirstSwitch1){
                    numFirst.playAnimation(numAnimation1)
                    numFirstSwitch1 = false
                    numFirstSwitch2 = true
                }
            } else if (firstNum == 0) {
                if(numFirstSwitch2){
                    numFirst.visible=false
                    numSecond.x = 240.0
                    numFirstSwitch1 = true
                    numFirstSwitch2 = false
                }
            }
            if (secondNum == 9) {
                if(numSecondSwitch1){
                    numSecond.playAnimation(numAnimation9)
                    numSecondSwitch1 = false
                    numSecondSwitch2 = true
                }
            } else if (secondNum == 8) {
                if(numSecondSwitch2){
                    numSecond.playAnimation(numAnimation8)
                    numSecondSwitch1 = true
                    numSecondSwitch2 = false
                }
            } else if (secondNum == 7) {
                if(numSecondSwitch1){
                    numSecond.playAnimation(numAnimation7)
                    numSecondSwitch1 = false
                    numSecondSwitch2 = true
                }
            } else if (secondNum == 6) {
                if(numSecondSwitch2){
                    numSecond.playAnimation(numAnimation6)
                    numSecondSwitch1 = true
                    numSecondSwitch2 = false
                }
            } else if (secondNum == 5) {
                if(numSecondSwitch1){
                    numSecond.playAnimation(numAnimation5)
                    numSecondSwitch1 = false
                    numSecondSwitch2 = true
                }
            } else if (secondNum == 4) {
                if(numSecondSwitch2){
                    numSecond.playAnimation(numAnimation4)
                    numSecondSwitch1 = true
                    numSecondSwitch2 = false
                }
            } else if (secondNum == 3) {
                if(numSecondSwitch1){
                    numSecond.playAnimation(numAnimation3)
                    numSecondSwitch1 = false
                    numSecondSwitch2 = true
                }
            } else if (secondNum == 2) {
                if(numSecondSwitch2){
                    numSecond.playAnimation(numAnimation2)
                    numSecondSwitch1 = true
                    numSecondSwitch2 = false
                }
            } else if (secondNum == 1) {
                if(numSecondSwitch1){
                    numSecond.playAnimation(numAnimation1)
                    numSecondSwitch1 = false
                    numSecondSwitch2 = true
                }
            } else if (secondNum == 0) {
                if(numSecondSwitch2){
                    numSecond.playAnimation(numAnimation0)
                    numSecondSwitch1 = true
                    numSecondSwitch2 = false
                }
            }
            if (points <= 0) {
                launchImmediately { sceneContainer.changeTo<End>() }
            }
        }
    }
}
