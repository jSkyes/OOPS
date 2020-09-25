package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    if (title != null){
      String titleClean = title.trim();
      if(!titleClean.equals("")){
        _title = titleClean;
      }else{
        throw new IllegalArgumentException();
      }
    }else{
      throw new IllegalArgumentException();
    }

    if (year > 1800 && year <5000){
      _year = year;
    }else{
      throw new IllegalArgumentException();
    }
    if (director!= null){
      String directorClean = director.trim();
      if(!directorClean.equals("")){
        _director = directorClean;
      }else{
        throw new IllegalArgumentException();
      }
    }else {
      throw new IllegalArgumentException();
    }
  }

  public String director() {
    return _director;
  }

  public String title() {
    return _title;
  }

  public int year() {
    return _year;
  }

  public boolean equals(Object thatObject) {
    //deep equality means that the objects compared are equal in all the fields
    //possibly include a test that compares for whether or not the obj passed in is actually an VideoObj
    try {
      VideoObj thatRec = (VideoObj) thatObject;
      //compare the director
      if(this._director.equals(thatRec._director)){
        if(this._title.equals(thatRec._title)){
          if(this._year == thatRec._year){
            return true;
          }
        }
      }
      return false;
    }catch (ClassCastException c){
      //in case the user tries to pass in an object that cannot be cast to record
      return false;
    }catch (IllegalArgumentException e){
      //in case the user tries to pass in an arguement that is NOT an object
      return false;
    }catch (NullPointerException n){
      //in case the user tries to pass in null for some bloody reason
      return false;
    }
  }

  public int hashCode() {
    int ans = 17;
    int titleHash = _title.hashCode();
    int dirHash = _director.hashCode();
    ans = (37 * ans + titleHash);
    ans = (37 * ans + _year);
    ans = (37 * ans + dirHash);
    return ans;
  }

  public int compareTo(Object thatObject) {
    //try to cast
    try{
      VideoObj thatVid = (VideoObj) thatObject;
      int lessThan = -1;
      int equals = 0;
      int greaterThan = 1;
      //compare title
      if (this._title.equals(thatVid._title) == true){
        //the title's are the same, keep a going!
        //compare year
        if(this._year == thatVid._year){
          //the year is the same, keep a going!
          //compare director
          if (this._director.equals(thatVid._director) == true){
            //they're all the SAME! return 0
            return equals;
          }else{
            //they're different directors! compare and return accordingly
            //string already has the method built into it
            int compAns = this._director.compareTo(thatVid._director);
            if (compAns > 0){
              //means it's greater than
              return greaterThan;
            }else{
              return lessThan;
            }
          }
        }else{
          //they're diff years!,  compare and return accordingly
          if (this._year < thatVid._year){
            return lessThan;
          }else{
            //this video is greater than that video
            return greaterThan;
          }
        }
      }else{
        //the title's aren't the same, compare and return accordingly
        //string already has the method built into it
        int compAns = this._title.compareTo(thatVid._title);
        if (compAns > 0){
          //means it's greater than
          return greaterThan;
        }else{
          return lessThan;
        }
      }
    }catch (ClassCastException c){
      System.out.println(c.getMessage());
      throw c;
    }
  }

  public String toString() {
    return this._title+ " ("+ this._year+") : " + this._director;
  }
}
