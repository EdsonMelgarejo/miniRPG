package com.example.minirpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import kotlin.random.Random

class MainActivity() : AppCompatActivity() {

    lateinit var bar_monster: ProgressBar
    lateinit var bar_hero: ProgressBar
    lateinit var lbl_log: TextView
    lateinit var lbl_finalMessage: TextView

    var monster_hp: Int = 100
    var hero_hp: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bar_monster = findViewById(R.id.bar_monster)
        bar_hero = findViewById(R.id.bar_hero)
        lbl_log = findViewById(R.id.lbl_log)
        lbl_finalMessage = findViewById(R.id.lbl_finalMessage)
    }

    fun ready(view : View){
        var txt_name = findViewById<EditText>(R.id.txt_name)
        var nombreJugador = txt_name.text

        if(nombreJugador.isEmpty()){
        } else {
            var lbl_hero = findViewById<TextView>(R.id.lbl_hero)
            lbl_hero.text = nombreJugador

            var layout_combat = findViewById<LinearLayout>(R.id.layout_combat)
            layout_combat.visibility = View.VISIBLE

            lbl_finalMessage.text = ""
            monster_hp = 100
            hero_hp = 100
            bar_monster.progress = monster_hp
            bar_hero.progress = hero_hp
        }
    }

    fun attack(view: View){
        var randomNumber = Random.nextInt(5 , 15)
        monster_hp -= randomNumber
        bar_monster.progress = monster_hp
        lbl_log.text = "Dañaste al mounstro: " + randomNumber.toString() + " pts"
        Handler().postDelayed({this.monster_attack(view)}, 2000)
    }

    fun ultimate(view: View){
        var randomNumber = Random.nextInt(15 , 30)
        monster_hp -= randomNumber
        bar_monster.progress = monster_hp
        lbl_log.text = "Dañaste al mounstro: " + randomNumber.toString() + " pts"
        Handler().postDelayed({this.monster_attack(view)}, 2000)
    }

    fun heal(view: View){
        var randomNumber = Random.nextInt(10 , 25)
        hero_hp += randomNumber
        bar_hero.progress = hero_hp
        lbl_log.text = "Te curaste: " + randomNumber.toString() + " pts"
        Handler().postDelayed({this.monster_attack(view)}, 2000)
    }

    fun monster_attack(view: View){
        if(monster_hp <= 0){
            lbl_finalMessage.text = "GANASTE"
        } else {
            var randomNumber = Random.nextInt(5 , 20)
            hero_hp -= randomNumber
            bar_hero.progress = hero_hp
            lbl_log.text = "Recibiste daño: " + randomNumber.toString() + " pts"
            if(hero_hp <= 0){
                lbl_finalMessage.text = "PERDISTE"
            }
        }

    }
}
