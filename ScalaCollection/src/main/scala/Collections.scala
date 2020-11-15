object Collections {
  // Main method
  def main(args: Array[String])
  {
    for (x <- 1 to 5)
    {
      var th = new MyThread()
      th.setName(x.toString())
      th.start()
    }
  }
}
// Scala code for thread creation by extending
// the Thread class
class MyThread extends Thread
{
  override def run()
  {
    // Displaying the thread that is running
    println("Thread " + Thread.currentThread().getName() +
      " is running.")
  }
}

