package grupoPM.projetoPaperRacing.Model;

/**
 * Interface do PathFinder.
 */
public interface PathFinder {
	public Path findPath(int sx, int sy, int tx, int ty);
}