package lab5;

public class Basketball
{
	private double diameter;
	private boolean inflated;
	
  public Basketball(double givenDiameter)
  {
	  diameter = givenDiameter;
	  inflated = false;
  }

  public boolean isDribbleable()
  {
    if (inflated)
    	return true;
    else
    	return false;
  }

  public double getDiameter()
  {
    return this.diameter;
  }

  public double getCircumference()
  {
	  return Math.PI * ((getDiameter() / 2) * (getDiameter() / 2));
  }

  public void inflate()
  {
	  inflated = true;
  }
}
