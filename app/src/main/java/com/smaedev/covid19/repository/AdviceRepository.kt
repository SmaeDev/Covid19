package com.smaedev.covid19.repository

import androidx.lifecycle.LiveData
import com.smaedev.covid19.db.*

class AdviceRepository(private val adviceDao: AdviceDao) {

    val allAdvices: LiveData<List<Advice>> = adviceDao.getAlphabetizedAdvices()

    fun fetchAdvices(){

        adviceDao.deleteAll()
        var advice = Advice("eva", "Distance"
            , "https://resources.finalsite.net/images/f_auto,q_auto,t_image_size_1/v1581014697/francaissf/uzxsyokx9ijinzgridit/flu_thumbnail.jpg", "Prenez une distance d'1m de chaque personne, évitez les embrassade et les salutations au toucher")
        adviceDao.insert(advice)

        var advice2 = Advice("Eternuement", "Eternuez et toussez dans le creux de la main"
            ,"https://resources.finalsite.net/images/f_auto,q_auto,t_image_size_1/v1581014697/francaissf/uzxsyokx9ijinzgridit/flu_thumbnail.jpg" ,"esa")
        adviceDao.insert(advice2)
        adviceDao.insert(Advice("Lavagae Main", "Lavez-vous régulièrement les mains"
            , "https://resources.finalsite.net/images/f_auto,q_auto,t_image_size_1/v1581014697/francaissf/uzxsyokx9ijinzgridit/flu_thumbnail.jpg"
            , "Lavez-vous bien les mains avec du savon ou avec un gel hydro-alcoolique"))
    }

}