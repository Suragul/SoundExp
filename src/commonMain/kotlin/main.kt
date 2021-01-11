import com.soywiz.korge.*
import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt

suspend fun main() = Korge(Korge.Config(module=ConfigModule))

object ConfigModule: Module(){
	override val bgcolor=Colors["#000"]
	override val size= SizeInt(720, 1280)
	override val mainScene=Intro::class

	override suspend fun AsyncInjector.configure(){
		mapPrototype { Intro() }
		mapPrototype { Game() }
		mapPrototype { End() }
		mapPrototype { Lose() }
	}
}