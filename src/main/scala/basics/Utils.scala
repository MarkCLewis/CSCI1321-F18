package basics

object Utils extends App {
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (List[A], Option[A]) = {
    def helper(rest: List[A]): (List[A], Option[A]) = rest match {
      case Nil => (Nil, None)
      case h::t =>
        if(pred(h)) (t, Some(h))
        else {
          val (lst2, a2) = helper(t)
          (h::lst2, a2)
        }
    }
    helper(lst)
  }
  
  val nums = List(6,4,8,6,4,2,3,7,8,5)
  println(findAndRemove(nums)(_%2==1))
}