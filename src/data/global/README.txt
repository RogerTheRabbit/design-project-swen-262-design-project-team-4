This is how the CSV files are formatted

artists: format with 3 fields per line as guid,name,disambiguation (disambiguation provides additional details, but may be blank)

songs: format with 4 fields per line as guid,artist-guid,duration,title. The title may contain spaces, commas, or other special characters

releases: format with a variable number of fields per line as guid,artist-guid,title,issue-date,medium,tracks where tracks is a comma-separated list of zero or more song GUIDs