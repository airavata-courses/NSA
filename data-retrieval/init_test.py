import nexradaws

def get_scans(self,years,months,days,radars):
    test_conn = nexradaws.NexradAwsInterface()
    scans = test_conn.get_avail_scans(years, months, days, radars);
    print(scans)
    print[scans[0]]
    return scans[0]  

print(get_scans())