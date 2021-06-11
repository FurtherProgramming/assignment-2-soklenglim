# Hotdesk
Hotdesk system is a system for user to book their desks on specific date. Many functions were implemented for users and admins to controll the booking.


# Home Page
- In the main page, it is the login page. Login page will deliver users to 2 type of pages which is one for user and the other for admin.
- Beside logging in, There is also a reset password for user in case they forget their password.
- If success, new password will be generated in numbers, so it's easy for user to change afterward.

![1](https://user-images.githubusercontent.com/69133654/121615170-9a2c3300-caa3-11eb-9083-bf2ca05093c7.PNG)
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
![11](https://user-images.githubusercontent.com/69133654/121616112-9699ab80-caa5-11eb-9997-659ad62bf1d7.PNG)
</br>
![2](https://user-images.githubusercontent.com/69133654/121615316-eecfae00-caa3-11eb-93e3-b6ccdb9c5912.PNG)
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
![3](https://user-images.githubusercontent.com/69133654/121615328-f5f6bc00-caa3-11eb-92c9-7b2bac995364.PNG)


# Booking Desk
- In booking section, user need to use the datepicker to pick a specific date, If any seats are booked, it will be displayed in red and yellow if it was locked.
- After selecting a seat and date, User will need to cofirm.
- Admin will use the same scene in to view all desk, and clicking any seats will lock the desk. And Select the lock seat, admin can unlock it back.
- Admin also can lock all seat of any day, but have to manually unlock it one by one.

![9](https://user-images.githubusercontent.com/69133654/121616585-7d452f00-caa6-11eb-9c8e-b0dc081dd625.PNG)
![13](https://user-images.githubusercontent.com/69133654/121617128-a5815d80-caa7-11eb-9d44-d1a9760598f5.PNG)
![Seat Confirm](https://user-images.githubusercontent.com/69133654/121616709-c1383400-caa6-11eb-8435-6b3aacc73b88.PNG)
</br>
# Edit Profile
- Update account function will display the detail of the login user, user can click the edit button to change any details.
- Password will not be shown on displaying display profile.
- Editing page have an admin checkbox which only displayed if admin role is logged in.</br>
![7](https://user-images.githubusercontent.com/69133654/121617373-1d4f8800-caa8-11eb-8368-53bc742f72c4.PNG)
![5](https://user-images.githubusercontent.com/69133654/121617378-22143c00-caa8-11eb-837b-191a73108556.PNG)
</br>

# View Booking
- After Booking, that seat status will be updated to pending which is waiting for admin to approve.
- Seat can be checked in only the status is approve but user can cancel seat at anytime.
- User can book seat only one at a time but can change only 48h after booking. 
- Admin release booking will display all the pending user, But there is also a display all booking button in case admin want to see all the booking.</br>

![8](https://user-images.githubusercontent.com/69133654/121617850-20974380-caa9-11eb-9d3f-98451c6fea2c.PNG)
![8](https://user-images.githubusercontent.com/69133654/121618042-77048200-caa9-11eb-9978-0e4e9bccd322.PNG)

# Admin Manage All Account
- Manage accounts function on admin side will list all users in a table.
- Admin can add new admin, edit users, and delete users.
- There is also another generated report for admin, it will generate all booking and user in .csv file. </br>

![4](https://user-images.githubusercontent.com/69133654/121618178-b632d300-caa9-11eb-8637-b9a0c8b76d44.PNG)
