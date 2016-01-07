ALTER TABLE `currentcy`.`email` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`email`, `code`);