package adp.tsp;

import java.awt.Point;

/**
 * A class that does the work of finding every possible route and which emits 
 * information about them to a TSPListener object. This class has a main method 
 * for demonstration purposes which just outputs to the command line.
 */
public class TSP {

  private final Point[] locations;

  private TSPRoute bestRoute;

  private TSPListener listener = new SysOutListener();

  public TSP(final int numberOfCities, final int width, final int height) {
    this.locations = new Point[numberOfCities];
    for (int i = 0; i < this.locations.length; i++) {
      final int x = (int)(Math.random() * (width - 20)) + 10;
      final int y = (int)(Math.random() * (height - 40)) + 30;
      this.locations[i] = new Point(x, y);
    } 
  }

  public void setListener(final TSPListener listener) {
    this.listener = listener;
  }

  /**
   * Computes all possible combinations of the input array, sending each
   * to output method in turn. 
   * Adapted from https://en.wikipedia.org/wiki/Heap%27s_algorithm
   * 
   * @param indexes
   */
  public void findShortestRoute() {

    final int[] indexes = new int[this.locations.length];
    for (int i = 0; i < indexes.length; i++) {
      indexes[i] = i;
    }

    final int[] c = new int[indexes.length];

    processRoute(indexes);

    int i = 1;
    while(i < indexes.length) {
      if (c[i] < i) {
        if ((i%2)==0) { // is even then
          swap(indexes, 0, i);
        } else {
          swap(indexes, c[i], i);
        }
        processRoute(indexes);
        c[i] += 1;
        i = 1;
      } else {
        c[i] = 0;
        i += 1;
      } 
    }

    this.listener.displayBest( this.bestRoute);
    System.out.println( "TSP all done!");
  }

  public static void swap(final int[] array, final int p, final int q) {
    final int s = array[p];
    array[p] = array[q];
    array[q] = s;
  }

  private void processRoute(final int[] path) {

    // filter only for paths commencing at 0, so we get 0,1,2 but not 1,2,0 or 2,0,1 which are all the same
    if (path[0] == 0) {
     
      final TSPRoute route = new TSPRoute(path, this.locations);

      if ((this.bestRoute == null) || (route.distance() < this.bestRoute.distance())) {
        this.bestRoute = route;
      }
      this.listener.displayUpdate(route, this.bestRoute);
    }
  }

  /** Demo main method for this class alone. */
  public static void main(final String[] args) {
    final TSP tsp = new TSP(4, 100, 100);
    tsp.findShortestRoute();
  }

    void findShortestRouteAnimated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean isRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private volatile boolean cancelled = false;

public void cancelTSP() {
    cancelled = true;
}

public void runTSP() {
    cancelled = false;
    while (!cancelled) {
        // Run the TSP algorithm
    }
}


}
