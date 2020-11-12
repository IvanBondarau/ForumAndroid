package by.bsuir.ivan_bondarau.forum.observer

interface Observable {
    fun attach(observer: Observer)
    fun notifyObserver()
}