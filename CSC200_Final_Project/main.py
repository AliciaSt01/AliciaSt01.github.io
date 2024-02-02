
# comments start with the hash.  
# Be sure to read these!

# Don't change this line:
from datetime import datetime

# Put your name here: Alicia StClair
import os

# Calls the 'banner' function
# Enters a 'while loop' to checks the user's input
# Calls the 'menu' function, stores the returns value into 'userSelect'
# If 'userSelect' is '1', continue through the loop and prints out the menu again
# If 'userSelect' is '2', calls the 'defaultReport' function
# If 'userSelect' is '3', calls the 'reportSingleStateDataByDate' function
# If 'userSelect' is '4', calls the 'totalMoralityByState' function
# If 'userSelect' is '5', calls the 'GetHighestMortality' function
# If 'userSelect' is '6', prints out a short message to the user and breaks from the loop, exiting the program
def main(): 
  banner()
  
  while True:
    userSelect = menu()

    if (userSelect == 1):
      continue
    elif (userSelect == 2):
      defaultReport()
    elif (userSelect == 3):
      reportSingleStateDataByDate()
    elif (userSelect == 4):
      totalMoralityByState()
    elif (userSelect == 5):
      GetHighestMortality()
    else:
      print("Program Exiting")
      break

# Accpets nothing
# Prints statements below as a banner 
# Returns nothing
def banner():
  print("------------------------------------------------------------------------------------------------------")
  print('''
 ██████╗██████╗  ██████╗     ██████╗ ██████╗ ██╗   ██╗██╗██████╗     ██████╗  █████╗ ████████╗ █████╗ 
██╔════╝██╔══██╗██╔════╝    ██╔════╝██╔═══██╗██║   ██║██║██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗
██║     ██║  ██║██║         ██║     ██║   ██║██║   ██║██║██║  ██║    ██║  ██║███████║   ██║   ███████║
██║     ██║  ██║██║         ██║     ██║   ██║╚██╗ ██╔╝██║██║  ██║    ██║  ██║██╔══██║   ██║   ██╔══██║
╚██████╗██████╔╝╚██████╗    ╚██████╗╚██████╔╝ ╚████╔╝ ██║██████╔╝    ██████╔╝██║  ██║   ██║   ██║  ██║
 ╚═════╝╚═════╝  ╚═════╝     ╚═════╝ ╚═════╝   ╚═══╝  ╚═╝╚═════╝     ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝
                                                                                                      ''')
  print("------------------------------------------------------------------------------------------------------")

# Accepts nothing
# Prints out the menu for the user
# Asks the user to pick one of the menu options
# Enters a while loop for user input
# Checks if the user entered a valid menu option
  # If the entered number is valid, breaks from the loop
  # If the entered number is not valid, print out message to the user and go back to the beginning of the loop
# Returns variable that stores the user's input
def menu():
  while True:
    print('''Mortality Rate Comparison Menu\n
    1. Show This Menu Again
    2. Full Mortality Report by State
    3. Mortality for a Single State, by Date Range
    4. Mortality Summary for all States
    5. Highest COVID mortality Week
    6. Exit''')

    print("\nMake your selection from the menu: ")
    select = int(input())
    print(" ")

    if (select in range(1,7)):
      break
    else:
      print("That is not a valid option. Please choose an option listed below.\n")
  
  return select    

# Accepts nothing
# Opens the file 'cdc.csv', saves into the file handler 'openFile'
# Loops through the file line by line
  # If line starts with the word 'Jurisdiction', part of the description and the last line before our deta,break from the loop
  # Else, skip the line and repeat the loop
# Returns the file handler, 'openFile'
def openCDCfile():
  openFile = open("cdc.csv", "r")

  for deleteLine in openFile:
    if "Jurisdiction" in deleteLine:
      break
    else:
      next(openFile) 

  return openFile

# Accepts nothing
# Initializes empty list, 'lineList'
# Calls the 'openCDCfile' function, saves the returns file handler into 'cdcFile'
# Creates and opens a new file 'reportFile'
# Writes the required date and headers into 'reportFile'
# Loops through 'cdcFile' line by line
# Formats the line
  # Strips the line
  # Splits the line where ',' are found
  # Formats the date to look like 'mm/dd/yy' and adds the final date product to 'displayDate'
  # Adds the current line to the list, 'lineList'
  # Checks for empty data, loops through the line and fills in with supplemental data if needed
  # Formats the lines and spaces and saves it into 'lineFormatting'
  # Writes the formatted lines into 'reportFile'
# Closes the files 'reportFile' and 'cdcFile'
# Prints message to the user
# Returns nothing
def defaultReport():
  lineList = []

  cdcFile = openCDCfile()
  os.remove("Full_Mortality_By_State_Report.txt") # This deletes the data from the file each run
  reportFile = open("Full_Mortality_By_State_Report.txt", "a")

  reportFile.write('''National Mortality Rate by Cause Listed by State and Reporting Date Report    Sorted by State''')
  now = datetime.now()
  dt_string = now.strftime("%m/%d/%Y     %H:%M:%S")
  reportFile.write('\nReport Generated: '+dt_string+'\n')

  reportFile.write('''                               WEEK          TOTAL         NATURAL      C19 MULTIPLE    C19 UNDERLYING
STATE                          ENDING        DEATHS        CAUSES          CAUSES           CAUSE\n''')
  reportFile.write("-----------------------------------------------------------------------------------------------------------\n")
  

  for line in cdcFile:
    count = 0 # Counter variable
    line = line.strip()
    line = line.split(',')

    dateLine = convertDate(line[3])
    displayDate = dateLine.date().strftime("%m/%d/%y");

    lineList.append(line)

    length = len(line) # Assigns the length of the line to the variable 'length'

    while True:
      if (count == length):
        break
      elif (len(line[count]) == 0):
        line[count] = '0'
        count += 1 # Adds '1' to 'count'
      else:
        count += 1 # Adds '1' to 'count'

    lineFormatting = line[0].ljust(25," ") + displayDate.rjust(14," ") + line[4].rjust(10," ") + line[5].rjust(15," ") + line[17].rjust(15," ") + line[18].rjust(16," ") + '\n'

    reportFile.write(lineFormatting)

  reportFile.close()
  cdcFile.close()
  
  print("File \"Full_Mortality_By_State_Report.txt\" has been written.\n")

# Accepts nothing 
# Calls getAvailableDates and displays them for the user, stores returned dates in 'startDate' & 'endDate'.
# Converts date strings into date objects, saves the date objects into 'sDateCompare' & 'eDateCompare'
# Asks user for start and end dates. Gives the option of automatically setting start and end dates with the letter 'S' and 'E'.
# Accepts user input and saves it into 'sDate' and 'eDate'
# Checks for date formatting validation in 'sDate' and 'eDate' by comapring them to 'sDateCompare' & 'eDateCompare'
  # If 'sDate' or 'eDate' are not correctly formatted, their respective loops print a user-friendly message and starts at the beginning of their loops
  # If 'sDate' or 'eDate' are correctly formatted, they break from their respective loops 
# Returns 'sDate' and 'eDate'
def getUserInputDates():
  startDate, endDate = getAvaliableDates()

  print("Reporting is availiable from",startDate,"to",endDate)

  sDateCompare = convertDate(startDate)
  eDateCompare = convertDate(endDate)

  while True:
    print("Choose your starting date in (mm/dd/yyyy) format, or S for the first date of the data: ")
    sDate = input()

    if sDate.capitalize() == 'S':
      sDate = startDate
      break
    else:
      try:
        sDateObj = convertDate(sDate)
        if (sDateObj < sDateCompare or sDateObj > eDateCompare):
          print("The date that you have entered has no data at this time. Please enter a different date.\n")
        else:
          break
      except ValueError:
        print("You entered an incorrect format. Please try again.\n")

  while True:
    print("Choose your starting date in (mm/dd/yyyy) format, or E for the last date of the data: ")
    eDate = input()
    if eDate.capitalize() == 'E':
      eDate = endDate
      break
    else:
      try:
        eDateObj = convertDate(eDate)
        if (eDateObj < sDateObj or eDateObj > eDateCompare):
          print("The date that you have entered has no data at this time. Please enter a different date.\n")
        else:
          break
      except ValueError:
        print("You entered an incorrect format. Please try again.\n")

  return sDate, eDate

# Calls the 'openCDCfile' function
# Initializes 'dateList' list
# Loops through 'cdcFile' and formats the document line by line
  # Strips 'line'
  # Splits the line by ','
  # Converts line[3], the line that holds the date, into a date object, saves into 'dateObj'
  # Appends 'dateObj' into 'dateList'
# Assgins the largest date in 'dateList' as 'endDate'
# Converts 'endDate' to a String, saves String in 'eDate'
# Assigns the smallest date in 'dateList' as 'startDate'
# Converts 'startDate' to a String, saves String in 'sDate'
# Returns 'sDate' and 'eDate'
def getAvaliableDates():
  cdcFile = openCDCfile()

  dateList = []  

  for line in cdcFile:
    line = line.strip()
    line = line.split(',')
    dateObj = convertDate(line[3])
    dateList.append(dateObj)

  endDate = max(dateList)
  eDate = str(endDate.date().strftime("%m/%d/%Y"));

  startDate = min(dateList)
  sDate = str(startDate.date().strftime("%m/%d/%Y"));


  return sDate,eDate

# Accepts nothing
# Calls the 'openCDCfile' function, saves the returns file handler into 'cdcFile'
# Initializes an empty list, 'stateList'
# Initializes boolean 'found' to False
# Initializes 'step' to '0'
# Enters while-loop for 'userState' verification
  # Asks user to enter a state as a string, saves input into 'userState'
  # Loops through 'cdcFile' to check for 'userState'
    # Strips the line
    # Splits the line where ',' are found
    # If 'userState' matches the data stored in state[0]
      # Appends 'state' into 'stateList'
      # 'found' equals True
      # Breaks from for-loop 
  # If 'found' equals True, breaks from while-loop
  # Else, prints a message to the user and breaks from while-loop
# If 'found' equals True
  # Calls 'getUserInputDates()' and gets user input dates, saves them into 'sDate' and 'eDate'
  # Creates and opens a new file 'reportFile'
  # Writes the required date and headers into 'reportFile'
  # Calls 'convertDate' and passes in 'sDate', 'eDate', and 'state' one at a time, saves returned date objects into 'sDateCompare', 'eDateCompare', and 'stateLineDate' respectively
  # Loops through 'cdcFile' line by line
  # Formats the line
    # Strips the line
    # Splits the line where ',' are found
    # If 'userState' equals the data stored in line[0]
    # Calls 'convertDate' and passes in the date stored in line[3], saves returned date objects into 'lineDateObj'
      # If 'lineDateObj' is in the range of 'sDateCompare' and 'eDateCompare'
        # If 'lineDateObj' does not equal 'stateLineDate', 'sDateCompare' equals 'stateLineDate' and 'step' is less than 1:
          # Formats the date to look like 'mm/dd/yy' and adds the final date product to 'displayDate'
          # Counts the length of 'line' and saves that value into 'length'
          # Checks for empty data, loops through the line and fills in with supplemental data if needed
          # Formats the lines and spaces and saves it into 'lineFormatting'
          # Writes the formatted lines into 'reportFile'
          # Adds 1 to 'step' counter
        # Formats the date to look like 'mm/dd/yy' and adds the final date product to 'displayDate'
        # Counts the length of 'line' and saves that value into 'length'
        # Checks for empty data, loops through the line and fills in with supplemental data if needed
        # Formats the lines and spaces and saves it into 'lineFormatting'
        # Writes the formatted lines into 'reportFile'
      # Else, moves to the next line of 'cdcFile'
    # Else, moves to the next line of 'cdcFile'
  # Prints message to the user
  # Returns nothing
def reportSingleStateDataByDate():
  cdcFile = openCDCfile()
  stateLine = []
  found = False
  step = 0

  while True:
    print("What State do you want to review? ")
    userState = input()

    for state in cdcFile:
      state = state.strip()
      state = state.split(',')

      if userState == state[0]:
        stateLine.append(state)
        found = True
        break

    if found == True:
      break
    else:
      print("\nThat state could not be found. Please try again.\n")
      break

  if found == True:
    sDate, eDate = getUserInputDates()

    os.remove("Mortality_For_State_By_Date_Range_Report.txt") # This deletes the data from the file each run
    reportFile = open("Mortality_For_State_By_Date_Range_Report.txt", "a")

    reportFile.write("Mortality for "+ userState +" by Date Range    For the selected date range "+ sDate +" - "+ eDate)
    now = datetime.now()
    dt_string = now.strftime("%m/%d/%Y     %H:%M:%S")
    reportFile.write('\nReport Generated: '+dt_string+'\n')

    reportFile.write('''                               WEEK          TOTAL         NATURAL      C19 MULTIPLE    C19 UNDERLYING\nSTATE                          ENDING        DEATHS        CAUSES          CAUSES           CAUSE\n''')
    reportFile.write("-----------------------------------------------------------------------------------------------------------\n")

    sDateCompare = convertDate(sDate)
    eDateCompare = convertDate(eDate)
    stateLineDate = convertDate(state[3])
    

    for line in cdcFile:
      count = 0
      line = line.strip()
      line = line.split(',')

      if (userState == line[0]):
        lineDateObj = convertDate(line[3])
        if (lineDateObj >= sDateCompare and lineDateObj <= eDateCompare):

          if (lineDateObj != stateLineDate and sDateCompare == stateLineDate and step < 1):
            displayDate = stateLineDate.date().strftime("%m/%d/%y")

            length = len(line) # Assigns the length of the line to the variable 'length'

            while True:
              if (count == length):
                break
              elif (len(line[count]) == 0):
                line[count] = '0'
                count += 1 # Adds '1' to 'count'
              else:
                count += 1 # Adds '1' to 'count'

            lineFormatting = line[0].ljust(25," ") + displayDate.rjust(14," ") + line[4].rjust(10," ") + line[5].rjust(15," ") + line[17].rjust(15," ") + line[18].rjust(16," ") + "\n"
  
            reportFile.write(lineFormatting)
            step += 1

          displayDate = lineDateObj.date().strftime("%m/%d/%y")

          length = len(line) # Assigns the length of the line to the variable 'length'

          while True:
            if (count == length):
              break
            elif (len(line[count]) == 0):
              line[count] = '0'
              count += 1 # Adds '1' to 'count'
            else:
              count += 1 # Adds '1' to 'count'

          lineFormatting = line[0].ljust(25," ") + displayDate.rjust(14," ") + line[4].rjust(10," ") + line[5].rjust(15," ") + line[17].rjust(15," ") + line[18].rjust(16," ") + "\n"
  
          reportFile.write(lineFormatting)

  
    print("File \"Mortality_For_State_By_Date_Range_Report.txt\" has been written.\n")

# Accepts nothing
# Initializes empty vairables, 'totalDeaths', 'natCauses', 'multCauses', 'underCauses'
# Calls the 'openCDCfile' function, saves the returns file handler into 'cdcFile'
# Calls 'getUserInputDates()' and gets user input dates, saves them into 'sDate' and 'eDate'
# Creates and opens a new file 'reportFile'
# Writes the required date and headers into 'reportFile'
# Enters a for-loop that reads the first line
  # Strips the line
  # Splits the line where ',' are located
  # Assigns the first line as the 'currentState'
  # breaks from loop
# Calls 'convertDate' and passes in 'sDate' and 'eDate' one at a time, saves returned date objects into 'sDateCompare' and 'eDateCompare' respectively
# Loops through 'cdcFile' line by line
# Formats the line
  # Strips the line
  # Splits the line where ',' are found
  # Calls 'convertDate' and passes in the data stored in 'line[3]', saves returned date objects into 'currentWeekDate'
  # Counts the length of 'line' and saves that value into 'length'
  # If 'currentState' does not equal the state data saved in line[0]
    # Calls 'printLineDetailReport' and passes in 'reportFile', 'currentState', 'totalDeaths', 'natCauses', 'multCauses', and 'underCauses'
    # Reassigns 'currentState' to the state date in line[0]
    # Prints a new line
    # Reinitializes empty vairables, 'totalDeaths', 'natCauses', 'multCauses', 'underCauses'
    # Checks for empty data, loops through the line and fills in with supplemental data if needed
    # Adds the total deaths data in the 'line' to 'totalDeaths', overiddes the data saved in 'totalDeaths'; repeats the same process with 'natCauses', 'multCauses', and 'underCauses'
  # Else if 'currentState' equals the state data saves in line[0] and is in the range of 'sDate' and 'eDate'
    # Checks for empty data, loops through the line and fills in with supplemental data if needed
    # Adds the total deaths data in the 'line' to 'totalDeaths', overiddes the data saved in 'totalDeaths'; repeats the same process with 'natCauses', 'multCauses', and 'underCauses'
# Calls 'printLineDetailReport' and passes in 'reportFile', 'currentState', 'totalDeaths', 'natCauses', 'multCauses', and 'underCauses'
# Returns nothing
def totalMoralityByState():
  totalDeaths = 0
  natCauses = 0
  multCauses = 0
  underCauses = 0
  cdcFile = openCDCfile()
  sDate, eDate = getUserInputDates()

  os.remove("Mortality_Summary_For_All_States_By_Date_Range_Report.txt") # This deletes the data from the file each run
  reportFile = open("Mortality_Summary_For_All_States_By_Date_Range_Report.txt", "a")

  reportFile.write("Mortality For All States by Date Range    For the selected date range "+ sDate +" - "+ eDate)
  now = datetime.now()
  dt_string = now.strftime("%m/%d/%Y     %H:%M:%S")
  reportFile.write('\nReport Generated: '+dt_string+'\n')

  reportFile.write('''                               TOTAL         NATURAL      C19 MULTIPLE    C19 UNDERLYING
STATE                          DEATHS        CAUSES          CAUSES           CAUSE\n''')
  reportFile.write("-----------------------------------------------------------------------------------------------------------\n")

  for states in cdcFile:
    states = states.strip()
    states = states.split(',')

    currentState = states[0]
    break

  sDate = convertDate(sDate)
  eDate = convertDate(eDate)

  for line in cdcFile:
    count = 0
    line = line.strip()
    line = line.split(',')
    currentWeekDate = convertDate(line[3])

    lineLength = len(line) # Assigns the length of the line to the variable 'length'

    if currentState != line[0]:
      printLineDetailReport(reportFile,currentState,totalDeaths,natCauses,multCauses,underCauses)
      currentState = line[0]
      reportFile.write("\n")

      totalDeaths = 0
      natCauses = 0
      multCauses = 0
      underCauses = 0

      while True:
        if (count == lineLength):
          break
        elif (len([count]) == 0):
          line[count] = 0
          count += 1 # Adds '1' to 'count'
        else:
          count += 1 # Adds '1' to 'count'

      totalDeaths = totalDeaths + int(line[4])
      natCauses = natCauses + int(line[5])
      multCauses = multCauses + int(line[17])
      underCauses = underCauses + int(line[18])
  
    elif currentState == line[0] and (currentWeekDate >= sDate and currentWeekDate <= eDate):

      while True:
        if (count == lineLength):
          break
        elif (len(line[count]) == 0):
          line[count] = 0
          count += 1 # Adds '1' to 'count'
        else:
          count += 1 # Adds '1' to 'count'

      totalDeaths = totalDeaths + int(line[4])
      natCauses = natCauses + int(line[5])
      multCauses = multCauses + int(line[17])
      underCauses = underCauses + int(line[18])

  printLineDetailReport(reportFile,currentState,totalDeaths,natCauses,multCauses,underCauses)
  print("File \"Mortality_Summary_For_All_States_By_Date_Range_Report.txt\" has been written")

# Accpets values stored in 'rFile', 'cState', 'tDeaths', 'nCauses', 'mCauses', and 'uCauses'
# Formats 'cState', 'tDeaths', 'nCauses', 'mCauses', and 'uCauses' data into one line
# Writes line to 'rFile'
# Returns nothing
def printLineDetailReport(rFile,cState,tDeaths,nCauses,mCauses,uCauses):
  lineFormatting = cState.ljust(25," ") + str(tDeaths).rjust(12," ") + str(nCauses).rjust(13," ") + str(mCauses).rjust(16," ") + str(uCauses).rjust(17," ")

  rFile.write(lineFormatting)

# Void function 
# Accepts nothing
# Calls 'openCDCfile' and is saved into file handler 'cdcFile'
# Enters another for-loops through that reads through the rest of the file
# Initializes counter variable 'count' to '0'
# Strips the line
# Splits the line where ',' are located
# Counts the length of 'line' and saves that value into 'length'
# Checks for empty data, loops through the line and fills in with supplemental data if needed
# If the underlying cause of death value is greater on the current line then it is than the value stored in 'largestUnder':
  # Assigns the current line as the largest underlying cause of death, saved into variable 'largestUnder'
  # Saves the state located in that line into 'state'
  # Saves the week located in that line into 'week'
# After going through the whole file, the 'week' is converted into a date object which is stored in 'dateObj'
# 'dateObj' is then formatted to look like MM/DD/YY, stored into 'displayWeek'
# Prints out 'largestUnder', 'state', and 'displayWeek' in a user-friendly message to the user
# Calculates a percentage using the total number of deaths that week and 'largestUnder', stores that value into 'percentDeath'
# Rounds 'percentDeath' and displays the data in a user-firendly message to the user
# Returns nothing
def GetHighestMortality():
  cdcFile = openCDCfile()

  for largest in cdcFile:
    largest = largest.strip()
    largest = largest.split(',')

    largestUnder = largest[18]
    state = largest[0]
    week = largest[3]
    break

  for line in cdcFile:
    count = 0
    line = line.strip()
    line = line.split(',')

    length = len(line) # Assigns the length of the line to the variable 'length'

    while True:
      if (count == length):
        break
      elif (len(line[count]) == 0):
        line[count] = '0'
        count += 1 # Adds '1' to 'count'
      else:
        count += 1 # Adds '1' to 'count'

    if int(line[18]) > int(largestUnder):
      largestUnder = line[18]
      totalDeaths = line[4]
      state = line[0]
      week = line[3]

  dateObj = convertDate(week)
  displayWeek = dateObj.date().strftime("%m/%d/%y")

  print("The largest number of deaths directly attributible to COVID 19 in this report range was " + largestUnder + " in " + state + " during the week of " + displayWeek + ".\n")

  percentDeath = float(float(largestUnder)/float(totalDeaths)*100)
  
  print("This represents ",round(percentDeath),"% of the total reported death in " + state + " that week.", sep=(""))




# Make no changes below this line!
# --------------------------------

###  HELPER FUNCTION FOR YOU ###

# use this function when you need to compare date strings
# you can't actually compare strings that "look" like dates because they aren't
# date objects. They won't sort like you expect them to.
# This function accepts date in mm/dd/yy format as a string
# returns date in yy/mm/dd format as a date object (not a string!!!)
def convertDate(dateString):
  objDate = datetime.strptime(dateString, '%m/%d/%Y')
  return objDate

# use this function to replace blank values with zero so that any math will work
def replaceSpaceWithZero(uString):
  if uString == '':
    uString = 0
  return int(uString)


# I've called main() for you.
# This line is a special way to call main
# that allows me to test your code.  Do not change it!
if __name__ == '__main__':
    main()

